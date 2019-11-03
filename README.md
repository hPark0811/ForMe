# ForMe
What's best financial advice FOR ME?

### Setup on AWS - C9
```
touch mongodb-org-3.6.repo
```
Open mongodb-org-3.6.repo and save below code
```
[mongodb-org-3.6]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/amazon/2013.03/mongodb-org/3.6/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-3.6.asc
```
```
sudo mv mongodb-org-3.6.repo /etc/yum.repos.d
sudo yum install -y mongodb-org
```
```
mkdir datda
echo 'mongod --dbpath=data --nojournal' > mongod
chmod a+x mongod
```
Run ``` ./mongod ```


## Understanding the Basic Structure and Flow of Program
***Flow List***
1. User logs into the app
2. Login fires a GET request to the server with customer_id (either hosted locally or online)
3. Server routes requests accordingly (server setup with (**NodeJS**) and (**ExpressJS**)
   - one goes to *TD_Davinci_API_Closest_Branch.py* where a GET request is made to **TD's Davinci API** to retrieve customer's and customer's nearest branch location in geographical coordinates
   ```python
      customer_details = requests.get(
         'https://api.td-davinci.com/api/customers/'+customer_id,
         headers = { 'Authorization': API_KEY }
      )
   ```
   - the other is processed within the server to query matching customer data from the database(**mongoDB**) which holds a demo data that was generated using K-mean algorithms from (**scikit-learn**)
  ![alt text](https://raw.githubusercontent.com/hPark0811/ForMe/master/server/Tools/KMEAN/graph/Food_Dining.png)
4. Retrieved data is then presented to the app's UI to display appropriate bank account/credit card information catered to each user along with a map that displays the location of a TD branch that is nearest to the user's home address (**GOOGLE MAPS API**)


