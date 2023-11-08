resource "aws_apprunner_service" "service" {
  service_name = var.prefix

  instance_configuration {
    instance_role_arn = aws_iam_role.instance.arn
  }

  source_configuration {
    authentication_configuration {
      access_role_arn = "arn:aws:iam::244530008913:role/service-role/AppRunnerECRAccessRole"
    }
    image_repository {
      image_configuration {
        port = "8080"
      }
      image_identifier      = var.image
      image_repository_type = "ECR"
    }
    auto_deployments_enabled = true
  }
}

resource "aws_iam_role" "rekognition_role" {
  name = "rekognition_full_access_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action = "sts:AssumeRole",
        Effect = "Allow",
        Principal = {
          Service = "rekognition.amazonaws.com"
        },
      },
    ],
  })
}


resource "aws_iam_policy_attachment" "rekognition_full_access" {
  name       = "rekognition_full_access"
  roles      = [aws_iam_role.rekognition_role.name]
  policy_arn = "arn:aws:iam::aws:policy/AmazonRekognitionFullAccess"
}