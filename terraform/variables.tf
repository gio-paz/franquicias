variable "aws_region" {
    description = "Región de AWS donde se desplegara la aplicación"
    type = string
    default = "us-east-1"
}

variable "app_name" {
    description = "Nombre de la aplicación"
    type        = string
    default     = "franquicias"
}

variable "db_password" {
    description = "Contraseña de la base de datos RDS"
    type        = string
    sensitive   = true
}