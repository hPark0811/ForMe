import requests
import json
from tqdm import tqdm

N = 1000

API_KEY = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiZjNiNDc0YWItYWRmNy0zMTIyLWEyMjAtYWQ5MTY4NDIzMjJhIiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiIyMDJiYjUyMi1iNGU2LTRkMDgtYTUyYS1kYTJmNmE2YTNkZDQifQ.t-Cys276DHjiBuOeL4C1-alBbWVpbSUo6BsUmPlTwg4'

response = requests.post(
	# post requests from TD Davinci
    'https://api.td-davinci.com/api/raw-customer-data',
    headers = { 'Authorization': API_KEY },
    json={ 'continuationToken': '' }
)

# json dictionary of customer information
customers = response.json()['result']['customers']

customers_dict = {}

for i,customer in tqdm(enumerate(customers)):
	if i == N:
		break
		
	transactions = requests.get(
		# get transaction information for each customer
        'https://api.td-davinci.com/api/customers/' + customer['id'] + '/transactions',
        headers = { 'Authorization': API_KEY }
    ).json()['result']
	
	transaction = {
		# Type: [total, frequency]
		"Food and Dining": [0, 0],
		"Shopping": [0, 0],
		"Home": [0, 0],
		"Entertainment":[0, 0],
		"Fees and Charges": [0, 0]
	}

	for trs in transactions:
		# if transaction type is in the transaction dictionary, update the value
		for tag in trs["categoryTags"]:
			if tag in transaction.keys():			
				transaction[tag][0] += trs["currencyAmount"]
				transaction[tag][1] += 1

	try:
		age = customer['age']
	except:
		# age = 0 means, customer has null age
		customer['age'] = 0
	try:
		income = customer['totalIncome']
	except:
		customer['totalIncome'] = 0

	customers_dict[customer['id']] = {
		'age':customer['age'],
		'totalIncome':customer['totalIncome'],
		'transaction':transaction
	}	


with open('customer.json', 'w') as customer_file:
	json.dump(customers_dict, customer_file, indent=2)

