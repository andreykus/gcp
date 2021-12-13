//output "ip" {
//  value = google_compute_instance.vm_instance.network_interface.0.network_ip
//}
//
//
//
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