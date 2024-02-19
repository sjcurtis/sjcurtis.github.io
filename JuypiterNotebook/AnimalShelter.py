from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
    """ CRUD operations for Animal collection in MongoDB """

    def __init__(self, userName, password):
        
        # Initializing the MongoClient. This helps to 
        # access the MongoDB databases and collections.
        # This is hard-wired to use the aac database, the 
        # animals collection, and the aac user.
        # Definitions of the connection string variables are
        # unique to the individual Apporto environment.
        #
        # You must edit the connection variables below to reflect
        # your own instance of MongoDB!
        #
        # Connection Variables
        #
        USER = userName # 'aacuser'
        PASS = password # 'SNHU1234'
        HOST = 'nv-desktop-services.apporto.com'
        PORT = 30851
        DB = 'AAC'
        COL = 'animals'
        #
        # Initialize Connection
        #
        self.client = MongoClient('mongodb://%s:%s@%s:%d' % (USER,PASS,HOST,PORT))
        self.database = self.client['%s' % (DB)]
        self.collection = self.database['%s' % (COL)]

    # Testing connection and debugging
    def findOne(self):
        return self.database.animals.find_one()
        
    # Used to insert data into the collection.
    # Returns True if data was inserted successfully, otherwise False.
    def create(self, data):
        if data is not None:        	
            result = self.database.animals.insert_one(data) # data should be dictionary  
            return result.acknowledged        
        else:
            raise Exception("Nothing to save, because data parameter is empty")

    # Used to query data from the collection.
    # Returns a list of results. List is empty if no results are found.
    def read(self, data):
        if data is not None:
            return list(self.database.animals.find(data))  # data should be dictionary            
        else:
            raise Exception("Nothing to search by, because data parameter is empty")
        
    # Used to update data in the collection.
    # Returns the number of modified records. 
    def updateOne(self, query, update):
        if query is not None:

            result = self.database.animals.update_one(query, update)
            
            return result.modified_count            
        else:
            raise Exception("Nothing to update, because data parameter is empty")
                
    # Used to update data in the collection.
    # Returns the number of modified records.   
    def updateMany(self, query, update):
        if query is not None:

            result = self.database.animals.update_many(query, update)
            
            return result.modified_count            
        else:
            raise Exception("Nothing to update, because data parameter is empty")
            
    # Used to delete data in the collection.
    # Returns the number of modified records.   
    def deleteMany(self, data):
        if data is not None:

            result = self.database.animals.delete_many(data)
            
            return result.deleted_count            
        else:
            raise Exception("Nothing to delete, because data parameter is empty")

