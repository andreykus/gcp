
provider "google" {

  credentials = file(var.credentials_file)
  project = var.project
  region = var.region
  zone = var.zone

}

resource "google_compute_network" "vpc_network" {
  name = "terraform-network"
}

#instance app vm
resource "google_compute_instance" "vm_instance" {
  name         = "app-instance"
  machine_type = "f1-micro"
  tags         = ["web", "dev"]

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-9"
    }
  }

  network_interface {
    network = google_compute_network.vpc_network.name
    access_config {
    }
  }

}

//#instance postgres
//resource "google_sql_database_instance" "master" {
//  name             = "master4"
//  database_version = "POSTGRES_12"
//  region           = var.region
//
//  settings {
//    tier = "db-f1-micro"
//  }
//}
//
//#on  potgres add database
//resource "google_sql_database" "database" {
//  name     = "udb"
//  instance = google_sql_database_instance.master.name
//}
//
//#potgres user
//resource "google_sql_user" "users" {
//  name     = "postgres"
//  instance = google_sql_database_instance.master.name
//  host     = "*"
//  password = "Curry000change"
//}
