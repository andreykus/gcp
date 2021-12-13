resource "google_cloud_run_service" "app_service" {
  name     = var.service_name
  location = var.region

  template {
    spec {
      service_account_name = var.service_account_email

      containers {
        image = var.docker_image_url
        env {
          name  = "POSTGRES_HOST"
          value = var.env_db_host
        }
      }
    }


    metadata {
      annotations = {
        "autoscaling.knative.dev/maxScale"        = "1"
        "run.googleapis.com/cloudsql-instances"   = var.cloudsql_connection_name
        "run.googleapis.com/vpc-access-connector" = var.vpc_connector
        "run.googleapis.com/vpc-access-egress"    = "private-ranges-only"
      }
    }
  }
  autogenerate_revision_name = true
}