node {
GTC_VERSION="1.2.7-TOCO"
PROJECT_NAME="composition"

if (env.UPDATE != null) {
  stage 'git'
  git 'https://github.com/glytoucan/WurcsToCompositionBatch.git'
  
  stage 'docker rm'
  sh 'docker-compose -f docker-compose.prod.yml rm -f'

  stage 'docker pull'
  sh 'PROJECT_NAME=' + PROJECT_NAME + ' GTC_VERSION=' + GTC_VERSION + ' docker-compose -f docker-compose.prod.yml pull'
}

stage 'run batch'
sh 'echo PROJECT_NAME=' + PROJECT_NAME + ' GTC_VERSION=' + GTC_VERSION + ' docker-compose -f docker-compose.prod.yml up --remove-orphans'
sh 'PROJECT_NAME=' + PROJECT_NAME + ' GTC_VERSION=' + GTC_VERSION + ' docker-compose -f docker-compose.prod.yml up --remove-orphans'

stage 'rm batch'
sh 'echo PROJECT_NAME=' + PROJECT_NAME + ' GTC_VERSION=' + GTC_VERSION + ' docker-compose -f docker-compose.prod.yml rm -f --all'
sh 'PROJECT_NAME=' + PROJECT_NAME + ' GTC_VERSION=' + GTC_VERSION + ' docker-compose -f docker-compose.prod.yml rm -f --all'

stage 'rm dangling volumes'
sh 'GTC_VERSION=' + GTC_VERSION + ' docker volume ls -qf dangling=true | xargs -r docker volume rm'
}
