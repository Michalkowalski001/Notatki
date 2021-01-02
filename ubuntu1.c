#include <stdio.h>
#include <unistd.h>
 
extern char **environ;
 
int main (int argc, char **argv, char **envp) {
    int p=0;
 
    printf("Poczatek procesu...\n");
    p = fork();
    printf("Tu jestem: %d\n", p);
 
    return(0);
}