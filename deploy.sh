#!/bin/bash

# --- CONFIGURATION ---
PROJECT_ID="devesh-192b2"  
REGION="us-central1"
REPO_NAME="movie-repo"
IMAGE_NAME="movie-chat"
TAG="latest"
IMAGE_URI="$REGION-docker.pkg.dev/$PROJECT_ID/$REPO_NAME/$IMAGE_NAME:$TAG"

echo "🚀 Starting Automated Deployment..."

# 1. Build the Docker Image
echo "📦 Building Docker Image..."
docker build -t $IMAGE_URI .

# 2. Configure Docker to authenticate with GCP
echo "🔑 Authenticating Docker..."
gcloud auth configure-docker $REGION-docker.pkg.dev --quiet

# 3. Push Image to Registry
echo "☁️ Pushing Image to Artifact Registry..."
docker push $IMAGE_URI

# 4. Deploy to Kubernetes
echo "☸️ Deploying to GKE..."
# Apply Database first
kubectl apply -f k8s/postgres.yaml
# Apply App and Service
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# 5. Force a restart of the app to pick up the new image
kubectl rollout restart deployment/movie-chat-app

echo "✅ Deployment Complete!"