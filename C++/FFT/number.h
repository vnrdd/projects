#ifndef NUMBER_H
#define NUMBER_H
#include <complex>
#include <fstream>
#include <iostream>
#include <vector>
typedef std::complex<long double> cld;

class number {
   private:
    std::vector<int> num;
    bool sign;  // 1 - POSITIVE, 0 - NEGATIVE

   public:
    number();
    number(const number &copy);
    friend void readNumbers(number &n1, number &n2, char *filename);
    void writeNumber(char *filename);
    std::vector<cld> makeComplexVector(size_t n) const;
    friend std::vector<int> makeIntVector(const std::vector<cld> &r, const number &a, const number &b);
    void makeNumber();
    number operator*(const number &b) const;
    friend std::ostream &operator<<(std::ostream &out, number &n) {
        if (n.sign == 0) out << "-";
        for (std::vector<int>::iterator it = n.num.begin(); it < n.num.end(); ++it) {
            out << *it;
        }
        return out;
    };
    ~number();
};

#endif  //NUMBER_H