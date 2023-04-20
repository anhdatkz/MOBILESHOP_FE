export function formatTien(n, currency) {
    return n.toFixed(1).replace(/(\d)(?=(\d{3})+\.)/g, "$1,") + currency;
}

export function caculate(data) {
    // return (data.gia - data.gia * data.ctGiamGiaLSP[0].phantram / 100)
    return (data.gia - data.gia * data.ctGiamGiaLSP[0].phantram / 100)
}

export const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});