#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER cinema;
    CREATE DATABASE cinema;
    ALTER USER cinema WITH encrypted PASSWORD 'cinema';
    GRANT ALL PRIVILEGES ON DATABASE cinema TO cinema;
EOSQL