# install the requests package using 'python -m pip install requests'
import requests
import json

API_KEY = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiZjNiNDc0YWItYWRmNy0zMTIyLWEyMjAtYWQ5MTY4NDIzMjJhIiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiIyMDJiYjUyMi1iNGU2LTRkMDgtYTUyYS1kYTJmNmE2YTNkZDQifQ.t-Cys276DHjiBuOeL4C1-alBbWVpbSUo6BsUmPlTwg4'

customer_details = requests.post(
    'https://api.td-davinci.com/api/raw-customer-data',
    headers = { 'Authorization': API_KEY },
    json={ 'continuationToken': '' }
)

customer_details = customer_details.json()['result']['customers']
customer_id_dict = {}

for customer in customer_details:
    customer_id = customer['id']
    if 'totalIncome' in customer:
        customer_income = customer['totalIncome']
    else:
        customer_income = 0

    customer_info_dict = {}
    customer_info_dict['income'] = customer_income

    #getting transactions per customer
    transactions = requests.get(
        'https://api.td-davinci.com/api/customers/' + customer_id + '/transactions',
        headers = { 'Authorization': API_KEY }
    )
    transactions = transactions.json()['result']
    for transaction in transactions:
        categories = transaction['categoryTags']
        if len(categories) == 1:
            if transaction['type'] == 'DepositAccountTransaction':
                transaction["currencyAmount"] = -1 * transaction["currencyAmount"]
                if 'Deposit Account Spending' in customer_info_dict:
                    customer_info_dict['Deposit Account Spending'] = customer_info_dict['Deposit Account Spending'] + transaction["currencyAmount"]
                else:
                    customer_info_dict['Deposit Account Spending'] = transaction["currencyAmount"]
            else:
                if 'Credit Card Transaction' in customer_info_dict:
                    customer_info_dict['Credit Card Transaction'] = customer_info_dict['Credit Card Transaction'] + transaction["currencyAmount"]
                else:
                    customer_info_dict['Credit Card Transaction'] = transaction["currencyAmount"]

            if categories[0] in customer_info_dict:
                customer_info_dict[categories[0]] = customer_info_dict[categories[0]] + transaction["currencyAmount"]
            else:
                customer_info_dict[categories[0]] = transaction["currencyAmount"]

            if (categories[0] + " count") in customer_info_dict:
                customer_info_dict[(categories[0] + " count")] = customer_info_dict[(categories[0] + " count")] + 1
            else:
                customer_info_dict[(categories[0] + " count")] = 0

    customer_id_dict[customer_id] = customer_info_dict
    print(customer_id)

#Now customer_ids has all the customer ids
print(customer_id_dict)

#Write result to result.json in same directory
with open('result.json', 'w') as fp:
    json.dump(customer_id_dict, fp)



