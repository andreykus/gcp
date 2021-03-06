

module "db" {
source = "./db"
vpc_id = var.vpc_id
region = var.region
db_name = var.db_name
databases = ["cinema"]
depends_on = [google_service_networking_connection.private_vpc_connection]
}


module "run_app_rest" {
source = "./run"
service_name = "cinema-rest"
region = var.region
docker_image_url = var.run_image
cloudsql_connection_name = module.db.proxy_connection
service_account_email = google_service_account.account.email
vpc_connector = google_vpc_access_connector.connector.self_link
env_db_host = module.db.db_address
env_db_db = "cinema"
env_db_logging = "1"
}





