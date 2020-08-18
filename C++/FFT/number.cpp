#include "number.h"
#include "FFT.h"

number::number() {
    sign = 1;
}

number::number(const number& copy) {
    num = copy.num;
    sign = copy.sign;
}

void readNumbers(number& n1, number& n2, char* filename) {
    std::ifstream f;
    f.open(filename);
    char c;
    while (c != '\n') {
        c = f.get();
        if (c == '\n')
            break;

        if (c == '-') {
            n1.sign = 0;
            continue;
        }
        n1.num.push_back(atoi(&c));
    }

    while (f.peek() != EOF) {
        c = f.get();
        if (c == '-') {
            n2.sign = 0;
            continue;
        }
        n2.num.push_back(atoi(&c));
    }
    f.close();
}

void number::writeNumber(char* filename) {
    std::ofstream f;
    f.open(filename);
    f << *this;
    f.close();
}

std::vector<cld> number::makeComplexVector(size_t n) const {
    std::vector<cld> ca(n, cld(0.0, 0.0));
    for (int i = 0; i < num.size(); ++i)
        ca[i] = cld(num[i], 0.0);

    return ca;
}

std::vector<int> makeIntVector(const std::vector<cld>& r, const number& a, const number& b) {
    std::vector<int> res(a.num.size() + b.num.size() - 1);
    for (int i = 0; i < res.size(); ++i) {
        res[i] = floorl(r[i].real() + 0.5);
    }
    return res;
}

number number::operator*(const number& b) const {
    number res;
    res.sign = writeSign(this->sign, b.sign);
    size_t n = setSize(std::max(this->num.size(), b.num.size()));

    std::vector<cld> ca = this->makeComplexVector(n);
    std::vector<cld> cb = b.makeComplexVector(n);

    ca = FFT(ca);
    cb = FFT(cb);

    for (int i = 0; i < n; ++i)
        ca[i] *= cb[i];
    std::vector<cld> cres = inverseFFT(ca);

    res.num = makeIntVector(cres, *this, b);
    res.makeNumber();

    return res;
}

void number::makeNumber() {
    int carry = 0;
    int i = 0;
    for (i = num.size() - 1; i > 0; --i) {
        num[i] += carry;
        carry = num[i] / 10;
        num[i] = num[i] % 10;
    }
    num[i] += carry;
}

number::~number() {
}