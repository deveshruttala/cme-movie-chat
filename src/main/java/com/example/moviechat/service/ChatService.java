package com.example.moviechat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final JdbcTemplate jdbcTemplate;

    // Inject the API Key from application.properties to check if it's real
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public ChatService(ChatClient.Builder builder, JdbcTemplate jdbcTemplate) {
        this.chatClient = builder.build();
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getAnswer(String userQuery) {
        // ---------------------------------------------------------
        // LOGIC: Check if we are in Mock Mode or Real AI Mode
        // ---------------------------------------------------------
        if (apiKey == null || apiKey.contains("INSERT_YOUR_OPENAI_API_KEY") || apiKey.trim().isEmpty()) {
            return runMockMode(userQuery);
        } else {
            return runRealAIMode(userQuery);
        }
    }

    // --- OPTION A: MOCK MODE (For Local Testing without paying) ---
    private Map<String, Object> runMockMode(String userQuery) {
        System.out.println("⚠️ Mock Mode: Using keyword search because API Key is missing.");

        // Simple logic: If user asks about Matrix, search Matrix. Else search Inception.
        String mockSql;
        if (userQuery.toLowerCase().contains("matrix")) {
            mockSql = "SELECT * FROM movies WHERE title ILIKE '%Matrix%'";
        } else if (userQuery.toLowerCase().contains("inception")) {
            mockSql = "SELECT * FROM movies WHERE title ILIKE '%Inception%'";
        } else {
            // Default fallback
            mockSql = "SELECT * FROM movies";
        }

        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(mockSql);
            return Map.of(
                    "query", userQuery,
                    "sql", mockSql,
                    "answer", result
            );
        } catch (Exception e) {
            return Map.of("error", "Database error: " + e.getMessage());
        }
    }

    // --- OPTION B: REAL AI MODE (Connects to OpenAI) ---
    private Map<String, Object> runRealAIMode(String userQuery) {
        String schemaPrompt = """
            You are an AI assistant that converts English questions into SQL queries for a PostgreSQL database.

            The Database Schema is:
            Table: movies (id, title, release_year, genre, director)
            Table: artists (id, name, birth_date)
            Table: movie_artists (id, movie_id, artist_id, role)

            Rules:
            1. Return ONLY the SQL query. Do not include markdown, backticks, or explanations.
            2. Use ONLY SELECT statements.
            3. Use ILIKE for case-insensitive text matching.

            Question: {question}
            """;

        PromptTemplate template = new PromptTemplate(schemaPrompt);
        template.add("question", userQuery);
        Prompt prompt = template.create();

        try {
            // 1. Call AI
            String sqlQuery = chatClient.prompt(prompt).call().content();

            // 2. Clean up AI response (remove ```sql and ```)
            sqlQuery = sqlQuery.replace("```sql", "").replace("```", "").trim();
            System.out.println("Generated SQL: " + sqlQuery);

            // 3. Run SQL
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sqlQuery);
            return Map.of("query", userQuery, "sql", sqlQuery, "answer", result);

        } catch (Exception e) {
            System.err.println("AI Error: " + e.getMessage());
            return Map.of("error", "AI generation failed. Check your API Key or Quota. Details: " + e.getMessage());
        }
    }
}