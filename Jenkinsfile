properties (
    [
        parameters(
            [
                string(defaultValue: '122.166.88.64', description: 'Control Machine Public IP', name: 'MY_IP', trim: true),
                string(defaultValue: 'RDAddrBookAngJS1', description: 'EC2 Instance name ', name: 'INSTANCE_NAME', trim: true),
                string(defaultValue: 'eu-west-1', description: 'AWS Region', name: 'AWS_REGION', trim: true),
                string(defaultValue: '/ec2.py', description: 'Ansible AWS dynamic inventory script', name: 'INVENTORY_SCRIPT', trim: true),
                string(defaultValue: '/ec2.ini', description: 'Ansible AWS dynamic inventory INI', name: 'INVENTORY_INI', trim: true)
            ]
        )
    ]
)
pipeline {
    agent any
    environment {
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
    }
    stages {
        stage('Get Launcher') {
            steps {
                git branch: 'master', url: 'git@github.com:rajdurvasula/RDAddrBookAngJS1.git', credentialsId: 'mykey1' 
            }
        }
        stage('Launch EC2 Instance for App') {
            steps {
                ansiblePlaybook(
                    playbook: 'ec2_site.yml',
                    colorized: true,
                    disableHostKeyChecking: true,
                    credentialsId: 'root_key_of_this_vm',
                    dynamicInventory: true,
                    extras: '-e aws_access_key=$AWS_ACCESS_KEY_ID -e aws_secret_key=$AWS_SECRET_ACCESS_KEY -e ec2_operation=launch_instance -e instance_name=${INSTANCE_NAME} -e sg_name=${INSTANCE_NAME} -e my_public_ip=${MY_IP} -e target_subnet_tag=${TARGET_SUBNET_TAG}')
            }
        }
        stage('RDAddrBook Prereqs') {
            steps {
                ansiblePlaybook(
                    playbook: 'ec2_app.yml',
                    colorized: true,
                    disableHostKeyChecking: true,
                    credentialsId: 'ec2-user',
                    dynamicInventory: true,
                    extras: '-e aws_access_key=$AWS_ACCESS_KEY_ID -e aws_secret_key=$AWS_SECRET_ACCESS_KEY -u ec2-user -e variable_host=tag_Name_$INSTANCE_NAME')
            }
        }
    }
}
