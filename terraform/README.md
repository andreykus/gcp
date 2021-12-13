
Infrastructura build
==========================

steps terraform:

    terraform init
    terraform fmt
    terraform validate
    terraform apply --
    terraform show
    
    terraform destroy --
    terraform output


Enable module
==========================

    gcloud services enable cloudresourcemanager.googleapis.com
    gcloud services enable cloudbilling.googleapis.com
    gcloud services enable iam.googleapis.com
    gcloud services enable compute.googleapis.com
    gcloud services enable sqladmin.googleapis.com
    
    gcloud services enable  Cloud Run API 
    gcloud services enable  API Gateway API
                            Serverless VPC Access API 
                            Service Networking API   servicenetworking.googleapis.com
    gcloud auth configure-docker
    

© Akvelon 2021