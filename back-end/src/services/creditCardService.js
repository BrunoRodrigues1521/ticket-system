class CreditCardService {

    constructor(cardData) {
        this.cardData = cardData;
    }

    async insert(userId,req) {
        try {
            const cardInfo = {
                userId: userId,
                cardNumber: req.cardNumber,
                type: req.type,
                expirationDate: Math.floor(new Date(req.expirationDate).getTime()/1000)
            }
            let result = await this.cardData.insert(cardInfo);
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async getByUserId(userId) {
        try {
            let result = await this.cardData.get(userId);
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async isExpired(expirationDate) {
        return expirationDate >= Math.floor(new Date().getTime()/1000);
    }

}
module.exports = CreditCardService;