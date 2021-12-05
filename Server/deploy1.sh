PROJECT_NETWORK='datastore-network'
SERVER1_IMAGE='datastore-server1-image'
SERVER1_CONTAINER='server1-container'
SERVER2_CONTAINER='server2-container'
SERVER0_CONTAINER='server0-container'

# clean up existing resources, if any
echo "----------Cleaning up existing resources----------"


# only cleanup
if [ "$1" == "cleanup-only" ]
then
  exit
fi

# run the image and open the required ports
echo "----------Running server app----------"
#docker run -it -d -p 11001:11001 --name $SERVER1_CONTAINER --network $PROJECT_NETWORK $SERVER1_IMAGE

docker run -it -d -p 11001:11001 --name $SERVER1_CONTAINER --network $PROJECT_NETWORK $SERVER1_IMAGE \
 java -jar server.jar $SERVER1_CONTAINER 11001 $SERVER2_CONTAINER 11002 $SERVER0_CONTAINER 11000

echo "----------watching logs from server----------"
docker logs $SERVER1_CONTAINER -f