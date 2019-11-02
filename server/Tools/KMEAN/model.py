import json
import numpy as np
import matplotlib.pyplot as plt

from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_score
from mpl_toolkits.mplot3d import Axes3D


def normalize(x):
	# normalize a list to (0, 1)
	x_min = np.min(x)
	x_max = np.max(x)
	n = []
	for x_i in x:
		n.append((x_i-x_min)/(x_max-x_min))
	return n


def load_data(path: str = "customer.json") -> np.array:
	# load customer information from customer.json
	with open(path) as customer_file:
		customer_data = list(json.load(customer_file).items())
		json_data = [obj[1] for obj in customer_data]

		customer_inputs = []
		# extracts json object to arrays
		for customer in json_data:
			customer_inputs.append([customer['age'],
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

		# normalize the customer array
		normed_customer_inputs = []
		for tagged in np.array(customer_inputs).T:
			normed_customer_inputs.append(normalize(tagged))

	return np.array(normed_customer_inputs).T


def model(k: int = 5):
	# retrieve data
	x = load_data()

	# KMeans Clustering Algorithms
	km = KMeans(n_clusters=k)

	n_data = x.shape[0]

	# 9 train 1 test
	train_set = x[0:int(n_data * 0.9)]
	test_set = x[int(n_data * 0.9 + 1): n_data]

	km.fit(train_set)

	return km, train_set, test_set


def silhouette(low: int = 2, high: int = 10):
	# evaluate the model using silhouette method
	silhouette_scores = []

	for n_clusters in range(low, high):
		# train the model with different n_clusters
		km, train_set, test_set = model(n_clusters)

		prediction = km.predict(test_set)

		# evaluate silhouette score
		score = silhouette_score(test_set, prediction)
		silhouette_scores.append(score)

		# print("silhouette_scores on k = "+str(n_clusters)+" "+str(score))

	# plot score over K
	x = np.linspace(low, high, high-low)
	y = silhouette_scores

	plt.xlabel("K")
	plt.ylabel("Score")
	plt.title("Silhouette")

	plt.plot(x, y)
	plt.savefig("graph/Silhouette.png")

	return silhouette_scores


def graph(model, X: list) -> None:
	DIR = "graph/"
	Y = model.predict(X)

	COLOURS = ["red", "green", "blue", "yellow", "black"]

	# constant
	z_axis = [customer[1] for customer in X]
	c_axis = [COLOURS[y] for y in Y]

	# income vs Food and Dining
	x_axis = [customer[2] for customer in X]
	y_axis = [customer[7] for customer in X]

	fig_food_and_dining = plt.figure()
	ax = fig_food_and_dining.gca(projection='3d')
	ax.scatter3D(x_axis, y_axis, z_axis, c=c_axis)
	ax.set_xlabel("Total payment")
	ax.set_ylabel("Frequency")
	ax.set_zlabel("Income")
	ax.set_title("Food and Dining")

	plt.savefig(DIR+"Food_Dining.png")
	plt.close()

	# income Shopping
	x_axis = [customer[3] for customer in X]
	y_axis = [customer[8] for customer in X]

	fig_shopping = plt.figure()
	ax = fig_shopping.gca(projection='3d')
	ax.scatter3D(x_axis, y_axis, z_axis, c=c_axis)
	ax.set_xlabel("Total payment")
	ax.set_ylabel("Frequency")
	ax.set_zlabel("Income")
	ax.set_title("Food and Dining")

	plt.savefig(DIR+"Shopping.png")
	plt.close()

	# income vs Home
	x_axis = [customer[4] for customer in X]
	y_axis = [customer[9] for customer in X]

	fig_home = plt.figure()
	ax = fig_home.gca(projection='3d')
	ax.scatter3D(x_axis, y_axis, z_axis, c=c_axis)
	ax.set_xlabel("Total payment")
	ax.set_ylabel("Frequency")
	ax.set_zlabel("Income")
	ax.set_title("Home")

	plt.savefig(DIR+"Home.png")
	plt.close()

	# income vs Entertainment

	x_axis = [customer[5] for customer in X]
	y_axis = [customer[10] for customer in X]

	fig_entertainment = plt.figure()
	ax = fig_entertainment.gca(projection='3d')
	ax.scatter3D(x_axis, y_axis, z_axis, c=c_axis)
	ax.set_xlabel("Total payment")
	ax.set_ylabel("Frequency")
	ax.set_zlabel("Income")
	ax.set_title("Entertainment")

	plt.savefig(DIR + "Entertainment.png")
	plt.close()

	# income vs  Fees and Charge
	x_axis = [customer[6] for customer in X]
	y_axis = [customer[11] for customer in X]

	fig_fees_and_charge = plt.figure()
	ax = fig_fees_and_charge.gca(projection='3d')
	ax.scatter3D(x_axis, y_axis, z_axis, c=c_axis)
	ax.set_xlabel("Total payment")
	ax.set_ylabel("Frequency")
	ax.set_zlabel("Income")
	ax.set_title("Fees and Charge")

	plt.savefig(DIR + "Fees_Charge.png")
	plt.close()


if __name__ == '__main__':
	silhouette(2, 30)
	kmean_model, train_set, test_set = model(5)
	graph(kmean_model, test_set)



