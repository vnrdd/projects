#include "number.h"

int main(int argc, char **argv) {
    number n1, n2;

    readNumbers(n1, n2, argv[1]);
    (n1 * n2).writeNumber(argv[2]);

    return 0;
}