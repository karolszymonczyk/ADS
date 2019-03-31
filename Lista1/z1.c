#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int counter = 0;

typedef struct node {
    int value;
    struct node *next;
} node;

void insert(node **head, int value) {

    node *new = NULL;
    new = malloc(sizeof(node));
  
    node *last = *head; 
   
    new->value  = value;
    new->next = NULL;

    if (*head == NULL) { 
       *head = new;
       return; 
    }   

    while (last->next != NULL) {

        last = last->next;
    }  
    last->next = new;

    return;
}

void delete(node **head, int value) {

	node *current = NULL;	
    current = malloc(sizeof(node));

	node *previous = NULL;
    previous = malloc(sizeof(node));

	previous = *head;
	current = previous->next;

	if(previous != NULL && previous->value == value) {

		*head = previous->next;
		free(previous);

		return;
	}	

	while(current != NULL && current->value != value) {

		previous = previous->next;
		current = previous->next;
	}

	if(current == NULL) {

		return;
	}
	
	previous->next = current->next;

	free(current);
}

int isEmpty(node **head) {

	return *head == NULL;
}

void findMTF(node **head, int value) {
    node *current = NULL;
    current = *head;

    node *headT = NULL;
    headT = *head;

    node *previous = NULL;
    previous = malloc(sizeof(node));

    counter++;
    counter++;

    if(current != NULL && current->value == value) {
        return;
    }

    counter++;
    counter++;

    while(current != NULL && current->value != value) {

        previous = current;
        current = current->next;

        counter++;
        counter++;
    }

    counter++;

    if(current == NULL) {
        return;
    }

    previous->next = current->next;

    *head = current;
    current->next = headT;

    return;
}

void findTRANS(node **head, int value) {

	node *current = NULL;
    current = *head;

	node *previous = NULL;
    previous = *head;

    node *beforePrevious = NULL;
    beforePrevious = malloc(sizeof(node));

    counter++;
    counter++;
    counter++;
    counter++;

    if(current->value == value || current->next == NULL) {
        counter--;
        counter--;
        return;
    }

    if(current->next != NULL && (current->next)->value == value) {

        findMTF(head, value);
        return;
    }

    counter++;
    counter++;
    while (current != NULL && current->value != value) {

        beforePrevious = previous;
        previous = beforePrevious->next;
        current = previous->next;

        counter++;
        counter++;
    }

    if(current == NULL) {
        return;
    }

    previous->next = current->next;
    current->next = previous;
    beforePrevious->next = current;
}

void printList(node *head) {

    node *current = head;

    while (current != NULL) {
        current = current->next;
    }
}

void swap (int *a, int *b) {

    int temp = *a; 
    *a = *b; 
    *b = temp; 
} 

void randomize (int arr[], int n ) { //FISHER-YATES

    srand (time(NULL)); 
  
    for (int i = n-1; i > 0; i--) { 

        int j = rand() % (i+1); 
        swap(&arr[i], &arr[j]); 
    } 
}

void prepareTable(int arr[], int n) {

	for(int i = 0; i < 100; i++) {
		arr[i] = i+1;
	}

    randomize (arr, n); 
}

int main() {

	node *head = NULL;

	int arr[100];
    int n = sizeof(arr)/ sizeof(arr[0]);

	prepareTable(arr, n);

	for (int i = 0; i < 100; i++) {
		insert(&head, arr[i]);   	
    }

     for (int i = 100; i > 0; i--) {
        for (int j = 1; j <= 100; j++) {
            findMTF(&head, j);
        }
        delete(&head, i);
     }

     printf("Liczba porównan metoda findMTF : %d\n", counter);
     counter = 0;

    for (int i = 0; i < 100; i++) {

		insert(&head, arr[i]);   	
    }

     for (int i = 100; i > 0; i--) {
        for (int j = 1; j <= 100; j++) {
            findTRANS(&head, j);
        }
        delete(&head, i);
     }

     printf("Liczba porównan metoda findTRANS : %d\n", counter);

	return 0;
}
