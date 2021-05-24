https://github.com/avamsykiran/Kafka_Java_Microservices_CTS.git
==========================================================================

Kafak
--------------------------------------------------

    Messaging System        on      Microservices

    Pre-requisite SKills
    ----------------------------

        Java 8
        Spring IoC
        Spring Context
        Spring Web - REST api
        Spring Web - MVC

    Brief Intro
    ----------------------
        Microservices

    Lab Setup
    --------------------------------------------------

        STS latest      IDE
        JDK 1.8         Dev Kit
        Maven 3         Build Tool
        SpringBoot 2.5  Dev Platform
        Kafka + ZooKeeper

    Monolithic App
    -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

        lack of scalability
        Tightly coupled system. Hard to update.
        Large Code base

     Microservices
     ---------------------------------------------------------------------

            is an eco system of simple (micro) apps,
            that cna interact with another seemlessly 
            and then provide a collective solution.

            Interoparability

            Challenges
            -----------------------------------------------------------

                Interactions
                Dynamic Scalability be in sink with the interactivity.
                Log / Req Tracing
                Handle Distrbuted Events        CQRS

    Message Systems
    ============================================================================

            Log Aggregation
            CQRS        
            Distributed Transaction
            Streams of Events

        PointA   --msg---[MSG System] ---msg--- PointB

        Message?
            is any object from an app or to an app.

            Event
            Instruction
            Action
            Data
            Signal
                    ......etc

        Producer?
            is the one who publishes(sends) a message

        Consumer?
            is the one who subscribes(waits and receives) the message

        Message System Types?
            Point-to-Point

                Producer  ----MSG ---->> [MSG SYS] ---MSG--->> CONSUMER  
                                (half - duplex)

            Publish - Subscribe 
                                                     ---MSG--->> CONSUMER 
                                                     ---MSG--->> CONSUMER 
                  Producer  ----MSG ---->> [MSG SYS] ---MSG--->> CONSUMER
                                                     ---MSG--->> CONSUMER 
                                                     ---MSG--->> CONSUMER   

        Apcche Kafka ---------> Pub-Sub Messaging System
        ---------------------------------------------------------------------------------

            Installation
            ------------------------------------------------------------------------
                dependency:     Java 8
                download: https://kafka.apache.org/downloads  Scala - 2.13 (tgz)

                Extract it to drive:/kafka

                create folder drive:/kafka/data/zookeeper       //state maintanence
                create folder drive:/kafka/data/kafka           //kafka server logs

                open drive:/kafka/config/zookeeper.properties
                set the below prop:
                        dataDir=drive:/kafka/data/zookeeper

                open drive:/kafka/config/server.properties
                set the below prop:
                       log.dirs=drive:/kafka/data/kafka


                INSTALLATION  IS DONE

            Start Up
            ------------------------------------------------------------------------

                Start ZooKeeper
                    drive:\kafka\bin\windows>zookeeper-server-start.bat ../../config/zookeeper.properties

                Start Kafka
                    drive:\kafka\bin\windows>kafka-server-start.bat ../../config/server.properties

            Shut Down
            -------------------------------------------------------------------------

                shutdown kafka
                shutdown zookeeper

            Archetecture
            -------------------------------------------------------------------------
                                            Kafka Eco System
                                              Clustur1
                                                Broker1
                Producer ---message----→            TopicA              ----message---► Consumer Group
                                                        Partition1                              Consumer1
                                                        Partition2                              Consumer2
                                                    TopicB
                                                        Partition1
                                                        Partition2

                                                Broker2
                                                    TopicA
                                                        Partition1
                                                        Partition2
                                                    TopicB
                                                        Partition1
                                                        Partition2

                   Producer ↔---broker Id----          Zookeeper            ----offset---► Consumer Group


                Clustur?
                        is a group of brokers..

                Topic?
                        1. is a logical channel of messages
                        2. a topic is resposnible to recive or to send
                            message of homoginous context.
                        3. each topic is identified by a unique name.
                        4. message when sent must be assosiated with the topic name from producer
                        5. a consumer can subscribe to a single topic through the topic name.
                        6. message in a topic irrespective of thir model (string/object/event) are
                            are modeled as an array of bytes (binary) in kafka.

                Broker?
                        1. is a execution unit that maintains the messages of a topic.
                        2. a single broker can manage one or more topics.
                        3. a broker is a stateless unit of process, that the broker
                                will not rememebr anything related to the communcation
                                like, who produced or who consumed or hom much is consumed.....
                        4. a broker is the reason behind the scalability and avialability of
                            messaging service on kafka.
                                as each broker can attend one consumer at a time, the more the 
                                number of broekr the more the number of consumers that can
                                be served.

                Partition?
                        1. a topic can be split into any number of partiions.
                        2. each partition can hold any number of messages.
                        3. there is limit on number of partitions.
                        4. the partition is selected to hold a message randomly, as long as the 
                            message has no assosiate key from the producer.
                        5. If the producer assosiates a message with a key, the partition
                            related to the key is selected/created and the message is pushed in it.
                        6. Each broker will have a copy of the partition and those copies
                           are called replacas.
                        7. Every broker need not have every partition or its replicas.
                        8. A partion is mastrly managed by one of these brokers and is called
                                the leader and other broker having the replicas are called followers.
                        9. The availability is ensured, as if the leader falls, the next follower will
                                becoem the leader automatically.

                            assuming replica = 2

                Broker1         Broker2         Broker3     Broker4
                    TopicA          TopicA          TopicA      TopicA
                        P1              P2              P3          P4
                        P2              P3              P4          P1

                TopicA P1   has Broker1 as leader and Broker4 as follower

                if Broekr 1 falls.....

                         assuming replica = 2

                Broker1  (falled) Broker2         Broker3     Broker4
                    TopicA          TopicA          TopicA      TopicA
                        P1              P2              P3          P4
                        P2              P3              P4          P1
                                        P1              P2
            
                 TopicA P1   has Broker4 as leader and Broker2 as follower
                        
            offset?

                is a serial number maintained by the zookeeper for 
                messages and consuemrs, to remeber, what is the 
                las message consumed by a consumer in  a consumer group.


    Kafka API
    =================================================================================

        Producer API        api for a producer to interact with Kafka
        Consumer API        api for a consumer to interact with Kafka
        Stream API          api allows the processing of the vents received on kafka,
        Connector API       api can interact with an underlying perssitant api
                            to act like an auotmatic producer or consumer.

    Kafka CLI
    =================================================================================

        bunch of .bat/.sh files are available as kafka cli tools,

        kafka-topics -zookeeper localhost:2181 -topic SAVE_TRAN -create -partitions 3 -replication-factor 1

        kafka-topics -zookeeper localhost:2181 -list
            
        kafka-topics -zookeeper localhost:2181 -describe --topic SAVE_TRAN

        kafka-topics -zookeeper localhost:2181 -topic SAVE_TRAN --delete

        Kafka-console-producer  -broker-list localhost:9092 -topic DEL_TRAN