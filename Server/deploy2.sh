PROJECT_NETWORK='datastore-network'
SERVER2_IMAGE='datastore-server2-image'
SERVER2_CONTAINER='server2-container'

# clean up existing resources, if any
echo "----------Cleaning up existing resources----------"
docker container stop $SERVER2_CONTAINER 2> /dev/null && docker container rm $SERVER2_CONTAINER 2> /dev/null

# only cleanup
if [ "$1" == "cleanup-only" ]
then
  exit
fi

# build the images from Dockerfile
echo "----------Building images----------"
docker build -t $SERVER2_IMAGE --target server2-build .

# run the image and open the required ports
echo "----------Running server app----------"
docker run -it -d -p 11002:11002 --name $SERVER2_CONTAINER --network $PROJECT_NETWORK $SERVER2_IMAGE

echo "----------watching logs from server----------"
docker logs $SERVER2_CONTAINER -f