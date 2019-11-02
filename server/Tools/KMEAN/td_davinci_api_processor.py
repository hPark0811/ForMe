import requests
import json
import numpy as np
from tqdm import tqdm


API_KEY = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiZjNiNDc0YWItYWRmNy0zMTIyLWEyMjAtYWQ5MTY4NDIzMjJhIiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiIyMDJiYjUyMi1iNGU2LTRkMDgtYTUyYS1kYTJmNmE2YTNkZDQifQ.t-Cys276DHjiBuOeL4C1-alBbWVpbSUo6BsUmPlTwg4'

def get_customer_info(id):
    response = requests.get('https://api.td-davinci.com/api/customers/'+id,
    headers = { 'Authorization': API_KEY },
    json={ 'continuationToken': '' })

    return response.json()['result']

def formated_to_array(customer):
    return np.array([customer['age'],
			customer['totalIncome'],
			customer['transaction']['Food and Dining'][0],
			customer['transaction']['Shopping'][0],
			customer['transaction']['Home'][0],
            customer['transaction']['Entertainment'][0],
			customer['transaction']['Fees and Charges'][0],
			customer['transaction']['Food and Dining'][1],
			customer['transaction']['Shopping'][1],
			customer['transaction']['Home'][1],
			customer['transaction']['Entertainment'][1],
			customer['transaction']['Fees and Charges'][1]])

def get_customer_array(id):
    # get transaction information for each customer
    customer_json = get_customer_info(id)
    transactions = requests.get(
        'https://api.td-davinci.com/api/customers/' + customer_json['id'] + '/transactions',
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
        age = customer_json['age']
    except: 
        # age = 0 means, customer has null age
        customer_json['age'] = 0
    try:
        income = customer_json['totalIncome']
    except:
        customer_json['totalIncome'] = 0

    customer = {
        'age':customer_json['age'],
		'totalIncome':customer_json['totalIncome'],
		'transaction':transaction
        }
    return formated_to_array(customer)


#print(get_customer_array("ce16ef42-a4e8-41e0-8f45-1ccf3079b659"))
#print(reformat_json(get_customer_info("660bc8b8-78ec-4636-9484-ad40d2c6162e")))