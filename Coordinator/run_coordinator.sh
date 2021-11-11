COORDINATOR_IMAGE='datastore-coordinator-image'
PROJECT_NETWORK='datastore-network'
SERVER_CONTAINER='server-container'
COORDINATOR_CONTAINER='coordinator-container'

# build the images from Dockerfile
echo "----------Building images----------"
docker build -t $COORDINATOR_IMAGE --target coordinator-build .

# run coordinator docker container with cmd args
echo "----------Running coordinator app----------"
docker run -it --rm -d -p 9090:9090 --name $COORDINATOR_CONTAINER --network $PROJECT_NETWORK $COORDINATOR_IMAGE \
 java -jar coordinator.jar $SERVER_CONTAINER 9091
