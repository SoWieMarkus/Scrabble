with open('wordlist-original.txt', "rb") as f:
    contents = f.read().decode("UTF-8")

Lines = contents.split("\n")

  
count = 0

filtered_words = []
filtered_words_alphabetical_order_of_letters = []

length_filter = True
letter_filter = True

list_of_one_time_letters = ["W", "Z", "P", "Ä","J", "Ü", "V"]

for line in Lines:
    line = line.replace("ß","ss")
    line = line.upper();
    line = line.strip()

    if len(line) > 9 and length_filter:
        continue

    if letter_filter:
        valid = True
        for letter in list_of_one_time_letters:
            if line.count(letter) > 3:
                valid = False
                break

        if not valid:
            continue

    filtered_words.append(line)

    alphabetical_order_of_letters_array = list(line)
    alphabetical_order_of_letters_array.sort()

    alphabetical_order_of_letters_word = ""
    for letter in alphabetical_order_of_letters_array:
        alphabetical_order_of_letters_word  = alphabetical_order_of_letters_word + letter


    filtered_words_alphabetical_order_of_letters.append(alphabetical_order_of_letters_word)


with open('./Scrabble/app/src/main/assets/words.txt', 'w') as f:
    for line in filtered_words:
        f.write(f"{line}\n")

with open('./Scrabble/app/src/main/assets/finder_words.txt', 'w') as f:
    for line in filtered_words_alphabetical_order_of_letters:
        f.write(f"{line}\n")

print(str(len(filtered_words)) + "/" + str(len(Lines)))