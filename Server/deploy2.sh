PROJECT_NETWORK='datastore-network'
SERVER2_IMAGE='datastore-server2-image'
SERVER2_CONTAINER='server2-container'
SERVER0_CONTAINER='server0-container'
SERVER1_CONTAINER='server1-container'

# clean up existing resources, if any
echo "----------Cleaning up existing resources----------"


# only cleanup
if [ "$1" == "cleanup-only" ]
then
  exit
fi

# run the image and open the required ports
echo "----------Running server app----------"
#docker run -it -d -p 11002:11002 --name $SERVER2_CONTAINER --network $PROJECT_NETWORK $SERVER2_IMAGE

docker run -it -d -p 11002:11002 --name $SERVER2_CONTAINER --network $PROJECT_NETWORK $SERVER2_IMAGE \
 java -jar server.jar $SERVER2_CONTAINER 11002 $SERVER0_CONTAINER 11000 $SERVER1_CONTAINER 11001

echo "----------watching logs from server----------"
docker logs $SERVER2_CONTAINER -f