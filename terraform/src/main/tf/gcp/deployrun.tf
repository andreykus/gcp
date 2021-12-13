//
//resource "google_cloud_run_service" "default" {
//  name     = "cloudrun-srv"
//  location = var.region
//
//  template {
//    spec {
//      containers {
//        image = "eu.gcr.io/steam-current-329713/cinema:1.0-SNAPSHOT"
//        env {
//          name = "POSTGRES_HOST"
//          value = "remote"
//        }
//        env {
//          name = "TARGEyesT"
//          value = "home"
//        }
//      }
//    }
//  }
//
//  metadata {
//    annotations = {
//      generated-by = "magic-modules"
//    }
//  }
//
//  traffic {
//    percent         = 100
//    latest_revision = true
//  }
//
//
//  lifecycle {
//    ignore_changes = [
//      metadata.0.annotations,
//    ]
//  }
//}




//resource "google_compute_network" "vpc_network" {
//  name = "terraform-network"
//}
//
//#instance app vm
//resource "google_compute_instance" "vm_instance" {
//  name         = "app-instance"
//  machine_type = "f1-micro"
//  tags         = ["web", "dev"]
//
//  boot_disk {
//    initialize_params {
//      image = "debian-cloud/debian-9"
//    }
//  }
//
//  network_interface {
//    network = google_compute_network.vpc_network.name
//    access_config {
//    }
//  }
//
//}