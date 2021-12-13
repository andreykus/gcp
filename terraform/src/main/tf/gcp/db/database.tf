resource "google_sql_database" "db_cinema" {
  for_each = var.databases
  charset   = "UTF8"
  collation = "en_US.UTF8"
  instance  = google_sql_database_instance.db_postgres.id
  name      = each.value
}