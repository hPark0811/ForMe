import sys
import json
from td_davinci_api_processor import get_customer_array
from model import load_data
import pickle
import numpy as np
from tqdm import tqdm
import matplotlib.pyplot as plt


PARAMS = [
    'age',
    'income',
    'food and dining',
    'shopping',
    'home',
    'entertainment',
    'fees and charges',
    'food and dining count',
    'shopping count',
    'home count',
    'entertainment count',
    'fees and charges count'
]

def get_estimate(n:int):
    """
        * rebuilding of model.py will reset the mapping of color to cards and account
        * rebuilding of model.py will need re-analyze of the data from graph, and re-implementation of get_estimate
    """
    cards:str
    account:str
    if n == 0:
        cards = "reward_visa"
        account = "chequing_account"
    elif n == 1:        
        cards = "cashback_infinite"
        account = "saving_account"
    elif n == 2:
        cards = "cashback"
        account = "chequeing_account"
    elif n == 3:
        cards = "cashback"
        account = "chequing_account"
    elif n == 4:
        cards = "aeroplane_visa_infinite"
        account = "chequeing_account"

    return [cards, account]


if __name__ == '__main__':
    with open('customer.json','r') as customer_file:
        customer_jsons = json.load(customer_file)
        with open('model.pkl', 'rb') as model_file:
            model = pickle.load(model_file)
            dic = {}
            for id in tqdm(customer_jsons.keys()):
                dic[id] = get_estimate(model.predict([get_customer_array(id)])[0])           
            with open('demo.json', 'w') as demo_file:
               json.dump(dic, demo_file, indent=2)


        


