# need enable api
#  https://console.developers.google.com/apis/api/iam.googleapis.com/overview?project=750903720791
resource "google_service_account" "account" {
  account_id   = var.service_account_name
  display_name = var.service_account_name
}

resource "google_project_iam_binding" "account_iam_binding" {
  for_each = toset([
"roles/cloudsql.client",
"roles/iam.serviceAccountTokenCreator"
])
role     = each.value

members = [
"serviceAccount:${google_service_account.account.email}"
]
}

resource "google_cloud_run_service_iam_member" "invoker_rest" {
service  = module.run_app_rest.service_name
role     = "roles/run.invoker"
member   = "allUsers"
location = var.region
}

