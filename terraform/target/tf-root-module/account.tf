# need enable api
#  https://console.developers.google.com/apis/api/iam.googleapis.com/overview?project=750903720791
resource "google_service_account" "account" {
  account_id   = var.service_account_name
  display_name = var.service_account_name
}