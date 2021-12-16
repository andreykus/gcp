variable "project_id" {
  type = string
  default = "eminent-crane-329517"
}

variable "region" {
  description = "region where the bucket will be created or the source region; defaults to europe-west1"
  type = string
  default = "europe-west1"
}

variable "zone" {
  type = string
  default = "europe-west1-b"
}

variable "service_account_name" {
  type = string
  default = "service-account-db1"
}

variable "credentials_file" {
  type = string
  default = "../../../../credent/steam-current-329713-c371a4147fdc.json"
}

# this is default network's VPC. Custom networks can't be deleted as there is a bug with cloud run
variable "vpc_id" {
  type = string
  default = "projects/eminent-crane-329517/global/networks/default"
}

variable "vpc_name" {
  type = string
  default = "default"
}

variable "vpc_connector_cidr" {
  type = string
  default = "10.1.0.0/28"
}

variable "db_name" {
  type = string
  default = "cinema-host-1"
}

variable "run_image" {
  type = string
  default = "eu.gcr.io/eminent-crane-329517/cinema:1.0-SNAPSHOT"
}

variable "run_name" {
  type = string
  default = "cinema-service"
}

