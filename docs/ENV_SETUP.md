# Environment Variables Configuration

This project uses environment variables to securely manage sensitive configuration data.

## Setup Instructions

1. **Create a `.env` file** in the root directory of the project (if not already present)
2. **Copy the contents** from `.env.example` to `.env`
3. **Update the values** in `.env` with your actual configuration:

   ```
   DB_URL=jdbc:mysql://localhost:3306/your_database_name
   DB_USERNAME=your_database_username
   DB_PASSWORD=your_database_password
   JWT_SECRET=your_jwt_secret_key_minimum_256_bits
   JWT_EXPIRATION=86400000
   ```

## Security Notes

- ⚠️ **NEVER commit the `.env` file** to version control
- The `.env` file is already listed in `.gitignore`
- Share `.env.example` instead, which contains no sensitive data
- Generate a strong JWT secret (at least 256 bits / 64 hex characters)

## Environment Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `DB_URL` | Database connection URL | `jdbc:mysql://localhost:3306/medicore_db` |
| `DB_USERNAME` | Database username | `root` |
| `DB_PASSWORD` | Database password | `your_secure_password` |
| `JWT_SECRET` | Secret key for JWT token signing | `64+ character hex string` |
| `JWT_EXPIRATION` | JWT token expiration time in milliseconds | `86400000` (24 hours) |

## Generating a Strong JWT Secret

You can generate a secure JWT secret using one of these methods:

### Using OpenSSL (Linux/Mac/Git Bash)
```bash
openssl rand -hex 64
```

### Using Node.js
```javascript
node -e "console.log(require('crypto').randomBytes(64).toString('hex'))"
```

### Using Python
```python
python -c "import secrets; print(secrets.token_hex(64))"
```

## Running the Application

Once your `.env` file is configured, run the application with:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Or on Windows:
```cmd
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

