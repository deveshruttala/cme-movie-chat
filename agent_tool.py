import subprocess
import sys
import shutil

# --- CONFIGURATION ---
PROJECT_ID = "devesh-192b2"  # Your Project ID
REGION = "us-central1"
REPO_NAME = "movie-repo"
IMAGE_NAME = "movie-chat"
TAG = "latest"

# The full address where the image will be stored in Google Cloud
IMAGE_URI = f"{REGION}-docker.pkg.dev/{PROJECT_ID}/{REPO_NAME}/{IMAGE_NAME}:{TAG}"

def run_command(command, description):
    print(f"\n🚀 {description}...")
    try:
        # shell=True allows this to run in Windows PowerShell/CMD
        subprocess.run(command, check=True, shell=True)
        print("✅ Success!")
    except subprocess.CalledProcessError as e:
        print(f"❌ Error during: {description}")
        print(f"   Command failed with exit code: {e.returncode}")
        sys.exit(1)

def deploy():
    print(f"🤖 Starting Automated Deployment for Project: {PROJECT_ID}")

    # 1. Check if Docker is running
    if not shutil.which("docker"):
        print("❌ Error: Docker is not found. Is Docker Desktop running?")
        sys.exit(1)

    # 2. Build the Docker Image
    run_command(f"docker build -t {IMAGE_URI} .", "Building Docker Image")

    # 3. Configure Docker Auth
    run_command(f"gcloud auth configure-docker {REGION}-docker.pkg.dev --quiet", "Authenticating Docker with GKE")

    # 4. Push the Image
    run_command(f"docker push {IMAGE_URI}", "Pushing Image to Google Artifact Registry")

    # 5. Deploy Database (Postgres)
    run_command("kubectl apply -f k8s/postgres.yaml", "Deploying Postgres Database to GKE")

    # 6. Deploy App & Service
    run_command("kubectl apply -f k8s/deployment.yaml", "Deploying Movie Chat App")
    run_command("kubectl apply -f k8s/service.yaml", "Exposing Service")

    # 7. Force Restart (to ensure it picks up the new image we just pushed)
    run_command("kubectl rollout restart deployment/movie-chat-app", "Restarting Pods to load new image")

    print("\n✨ Deployment Sequence Complete!")
    print("⏳ Wait 1-2 minutes, then run 'kubectl get service movie-chat-service' to get your URL.")

if __name__ == "__main__":
    deploy()