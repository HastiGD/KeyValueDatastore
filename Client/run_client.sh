PROJECT_NETWORK='datastore-network'
SERVER0_CONTAINER='server0-container'
CLIENT_IMAGE='datastore-client-image'
CLIENT_CONTAINER='client-container'

# build the images from Dockerfile
echo "----------Building images----------"
docker build -t $CLIENT_IMAGE --target client-build .

# run client docker container with cmd args
echo "----------Running client app----------"
docker run -it --rm --name $CLIENT_CONTAINER --network $PROJECT_NETWORK $CLIENT_IMAGE \
 java -jar client.jar $SERVER0_CONTAINER 11000
