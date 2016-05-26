# file = open('products.txt', 'r')
# products_id = open('products_id.txt', 'w')
# index = 0
# for item in file.readlines():
#     products_id.write(item.rstrip('\n') +' ' + str("%05d" % index) + '\n')
#     index = index + 1


# file = open('ratings.csv', 'r')
# user_id = open('user_id.txt', 'w')
# userlist = []
# for item in file.readlines():
#     list = item.split(',')
#     userlist.append(list[0])
# index = 0
# for user in set(userlist):
#     user_id.write(user + ' ' + str("%06d" % index) + '\n')
#     index = index + 1
# file.close()
# user_id.close()

products_id = open('products_id.txt', 'r')
user_id = open('user_id.txt', 'r')
products_dict = {}
user_dict = {}
for product in products_id.readlines():
    list = product.split()
    products_dict[list[0]] = list[1]
for user in user_id.readlines():
    list = user.split()
    user_dict[list[0]] = list[1]

file = open('ratings.csv', 'r')
output = open('item.csv', 'w')
for item in file.readlines():
    list = item.split(',')
    product = products_dict[list[1]]
    user = user_dict[list[0]]
    output.write(user+','+product+','+list[2])
file.close()
output.close()
user_id.close()
products_id.close()