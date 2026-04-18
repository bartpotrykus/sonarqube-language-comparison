import sqlite3
import subprocess
import hashlib

# Podatność 1: SQL Injection
def get_user(username):
    conn = sqlite3.connect("users.db")
    cursor = conn.cursor()
    query = "SELECT * FROM users WHERE username = '" + username + "'"
    cursor.execute(query)
    return cursor.fetchall()

# Podatność 2: Hardcoded credentials
DB_PASSWORD = "admin123"
API_KEY = "sk-prod-abc123xyz789secret"

# Podatność 3: Weak hashing (MD5)
def hash_password(password):
    return hashlib.md5(password.encode()).hexdigest()

# Podatność 4: Command injection
def ping_host(host):
    result = subprocess.run("ping " + host, shell=True)
    return result