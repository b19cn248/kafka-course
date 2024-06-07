kafka-topics.sh --bootstrap-server localhost:9092 --list

echo -e 'Deleting kafka topics'
kafka-topics.sh --bootstrap-server localhost:9092 --topic payment-request --delete --if-exists
kafka-topics.sh --bootstrap-server localhost:9092 --topic payment-response --delete --if-exists
kafka-topics.sh --bootstrap-server localhost:9092 --topic restaurant-approval-request --delete --if-exists
kafka-topics.sh --bootstrap-server localhost:9092 --topic restaurant-approval-response --delete --if-exists
kafka-topics.sh --bootstrap-server localhost:9092 --topic customer --delete --if-exists

echo -e 'Creating kafka topics'
kafka-topics.sh --bootstrap-server localhost:9092 --create --if-not-exists --topic payment-request --replication-factor 3 --partitions 3
kafka-topics.sh --bootstrap-server localhost:9092 --create --if-not-exists --topic payment-response --replication-factor 3 --partitions 3
kafka-topics.sh --bootstrap-server localhost:9092 --create --if-not-exists --topic restaurant-approval-request --replication-factor 3 --partitions 3
kafka-topics.sh --bootstrap-server localhost:9092 --create --if-not-exists --topic restaurant-approval-response --replication-factor 3 --partitions 3
kafka-topics.sh --bootstrap-server localhost:9092 --create --if-not-exists --topic customer --replication-factor 3 --partitions 3

echo -e 'Successfully created the following topics:'
kafka-topics.sh --bootstrap-server localhost:9092 --list
