dbuild:
	docker build -t ${ECR_REPO}:$(shell ./version.sh) .
dpush:
	docker push ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com/${ECR_REPO}:$(shell ./version.sh)
dtag:
	docker tag demoback:$(shell ./version.sh) ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com/${ECR_REPO}:$(shell ./version.sh)
drun:
	docker run --rm -it -p 8080:8080 ${ECR_REPO}:$(shell ./version.sh)