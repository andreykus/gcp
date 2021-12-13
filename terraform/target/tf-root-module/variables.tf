variable "project" {
  type = string
  default = "steam-current-329713"
}

variable "credentials_file" {
  type = string
  default = "../../../../credent/steam-current-329713-c371a4147fdc.json"
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
  default = "service-account-db"
}

variable "environment" {
  description = "value of the 'Environment' tag"
  type        = string
  default     = "dev"
}