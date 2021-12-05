PROJECT_NETWORK='datastore-network'
SERVER1_IMAGE='datastore-server1-image'
SERVER1_CONTAINER='server1-container'

# clean up existing resources, if any
echo "----------Cleaning up existing resources----------"
docker container stop $SERVER1_CONTAINER 2> /dev/null && docker container rm $SERVER1_CONTAINER 2> /dev/null

# only cleanup
if [ "$1" == "cleanup-only" ]
then
  exit
fi

# build the images from Dockerfile
echo "----------Building images----------"
docker build -t $SERVER1_IMAGE --target server1-build .

# run the image and open the required ports
echo "----------Running server app----------"
docker run -it -d -p 11001:11001 --name $SERVER1_CONTAINER --network $PROJECT_NETWORK $SERVER1_IMAGE

echo "----------watching logs from server----------"
docker logs $SERVER1_CONTAINER -f