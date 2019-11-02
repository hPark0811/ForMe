# install the requests package using 'python -m pip install requests'
import json
import math
import webbrowser
import sys

API_KEY = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiZjNiNDc0YWItYWRmNy0zMTIyLWEyMjAtYWQ5MTY4NDIzMjJhIiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiIyMDJiYjUyMi1iNGU2LTRkMDgtYTUyYS1kYTJmNmE2YTNkZDQifQ.t-Cys276DHjiBuOeL4C1-alBbWVpbSUo6BsUmPlTwg4'

customer_loc = []
def customerFn():
    import requests
    # customer_id = input("Customer ID: ")
    # type(customer_id)
    customer_id = sys.argv[1]
    # print("sys.argv[1]: "+customer_id)

    customer_details = requests.get(
        'https://api.td-davinci.com/api/customers/'+customer_id,
        headers = { 'Authorization': API_KEY }
    )

    customer_details = customer_details.json()['result']['addresses']['principalResidence']

    # print(customer_details)
    # print("CUSTOMER'S ADDRESS: ",customer_details['longitude'], customer_details['latitude'])

    customer_loc.append(customer_details['longitude'])
    customer_loc.append(customer_details['latitude'])

    # return customer_loc[1]+","+customer_loc[0]

def branchFn():
    # for coordinates in customer_loc:
    #     print("CUSTOMERS ADDRESS IS STILL: ", customer_loc[0], customer_loc[1])


    import requests

    branches = requests.get(
        'https://api.td-davinci.com/api/branches',
        headers = { 'Authorization': API_KEY }
    )

    branches_deets = branches.json()['result']

    #long/lat of first element of all branches
    lngMin = branches_deets[0]['lng']
    latMin = branches_deets[0]['lat']

    # print("firstTemp of branches: "+lngMin, latMin)

    for branch in branches_deets:
        branch_id = branch['id']

        branch_long = branch['lng']
        branch_lat = branch['lat']


        left = math.sqrt(((float(branch_long)-float(customer_loc[0]))**2)+((float(branch_lat)-float(customer_loc[1]))**2))
        right = math.sqrt(((float(lngMin)-customer_loc[0]))**2)+math.sqrt(((float(latMin)-customer_loc[1]))**2)
        if left<right:
            lngMin = branch_long
            latMin = branch_lat
        #     print("CHANGED TO ", lngMin, latMin)
        # else:
        #     print("PASS:",branch_long, branch_lat)

        
    # print("FINAL:",lngMin, latMin)
    
    return latMin+","+lngMin


customerFn()
# branchFn()


mapsURL = "https://www.google.com/maps/search/?api=1&query="+branchFn()
directionsURL = "https://www.google.com/maps/dir/?api=1&origin="+str(customer_loc[1])+","+str(customer_loc[0])+"&destination="+branchFn()+"&travelmode=bicycling"

#opens corresponding google maps on web browser - for computers
# webbrowser.open(directionsURL)

# print(directionsURL)

print(str(customer_loc[1])+", "+str(customer_loc[0]))