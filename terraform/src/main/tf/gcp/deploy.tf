//resource "google_storage_bucket" "website-bucket-1632067139789" {
//  name = "appengine"
//}
//
//resource "google_storage_bucket_object" "cinema-object-1632067139789" {
//  name = "cinema.jar"
//  bucket = "${google_storage_bucket.website-bucket-1632067139789.name}"
//  source = "../../../../../cinema/target/cinema-1.0-SNAPSHOT.jar"
//}
//
//resource "google_app_engine_standard_app_version" "myapp_v1" {
//  version_id = "v1"
//  service = "myapp"
//  name="aa"
//  runtime = java11
//
//
//  delete_service_on_destroy = true
//
//  deployment {
//    zip {
//      source_url = "https://storage.googleapis.com/${google_storage_bucket.website-bucket-1632067139789.name}}/${google_storage_bucket_object.cinema-object-1632067139789.name}"
//    }
//  }
//  env_variables = {
//    port = "8080"
//  }
//  depends_on = [
//    "google_storage_bucket_object.cinema-object-1632067139789"]
//}


