import * as crypto from "crypto";
import { exec } from "child_process";

// Podatność 2: Hardcoded credentials
const DB_PASSWORD = "admin123";
const API_KEY = "sk-prod-abc123xyz789secret";
const DB_URL = "mysql://localhost/mydb";

// Podatność 1: SQL Injection
async function getUser(username: string): Promise<void> {
    const mysql = require("mysql2");
    const conn = mysql.createConnection(DB_URL);
    const query = "SELECT * FROM users WHERE username = '" + username + "'";
    conn.query(query);
}

// Podatność 3: Weak hashing (MD5)
function hashPassword(password: string): string {
    return crypto.createHash("md5").update(password).digest("hex");
}

// Podatność 4: Command injection
function pingHost(host: string): void {
    exec("ping " + host);
}

getUser("admin");
hashPassword("secret");
pingHost("google.com");