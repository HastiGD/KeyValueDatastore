PROJECT_NETWORK='datastore-network'
SERVER0_IMAGE='datastore-server0-image'
SERVER0_CONTAINER='server0-container'
CLIENT_IMAGE='client-image'
CLIENT_CONTAINER='client-container'

# clean up existing resources, if any
echo "----------Cleaning up existing resources----------"
docker container stop $SERVER0_CONTAINER 2> /dev/null && docker container rm $SERVER0_CONTAINER 2> /dev/null
docker container stop $CLIENT_CONTAINER 2> /dev/null && docker container rm $CLIENT_CONTAINER 2> /dev/null
docker network rm $PROJECT_NETWORK 2> /dev/null

# only cleanup
if [ "$1" == "cleanup-only" ]
then
  exit
fi

# create a custom virtual network
echo "----------creating a virtual network----------"
docker network create $PROJECT_NETWORK

# build the images from Dockerfile
echo "----------Building images----------"
docker build -t $SERVER0_IMAGE --target server0-build .

# run the image and open the required ports
echo "----------Running server app----------"
docker run -it -d -p 11000:11000 --name $SERVER0_CONTAINER --network $PROJECT_NETWORK $SERVER0_IMAGE

echo "----------watching logs from server----------"
docker logs $SERVER0_CONTAINER -f