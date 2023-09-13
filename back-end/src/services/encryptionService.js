var crypto = require('crypto');

class EncryptionService{

    constructor(crypto){
        this.crypto = crypto;
    }
    rsaEncrypt(data, privateKey){
        const encrypted = this.crypto.privateEncrypt(privateKey,Buffer.from(data));
        return encrypted.toString('base64');
    }
    rsaDecrypt(encryptedData, publicKey){
        const decrypted = this.crypto.publicDecrypt(publicKey,Buffer.from(encryptedData, 'base64'));
        return decrypted.toString('utf8');
    }
}

/* Test logic */
 const privateKey = 
 "-----BEGIN RSA PRIVATE KEY-----\n"+
 "MIIEpQIBAAKCAQEAlAyXPCWjgKT5+ai0/rdQP6v7vftFGZN758n+mjDxTF+xqFKg\n"+
 "27JlEsSrlsigx8HBZi9SdI/5MG4MuNTIYi3wezKx477K6KBKesrv5uceyvXU+VtM\n"+
 "/8Dkq5NB4FgdJW0kY1ij0e7cIomZGqEbMy5/dRMiUHv5PK9DfpxJETmIwW1//2Tg\n"+
 "jU8LVReJmcMdaFEMZavJ+qISYnyO5VAavvryVgznJfYBhl7nmJ8fgWjhr+znB8i3\n"+
 "RrB05rrwI03mB9TyT1o/hHj1lIWpau4LMjKfCRF+Nz7nfM2uYdYD87iDM6qGdAcT\n"+
 "8AkbzIhuc7xKkYjjkS/y4MEob4gbYNFN2/tRhwIDAQABAoIBAC7bRUpOB0+B53Py\n"+
 "x0YOsqFUR1q7mgUcPoz/y0r1zokDiHDjpfGipoaoJD6WxPw0AIfc1lX58z42tmQR\n"+
 "RFJ+g6waQV75VbMGeEwgyojkAUTvRTaehOI1JKqA8sqUWLUts+SVe1PIK3fq4MAi\n"+
 "2T77qm9dXPDoK9vfBJef0yhWc0f0amaQA2+IOFLyq+mc6SVvUT1+KTkmirsGpyL5\n"+
 "yVow0OrCOcZO4uHmCMaN9oVtTeIx+ks+1f0itqgY6qSoiHwD5e0DNL7ypZ8MlAqW\n"+
 "qEKHMQCLxR2sbwdjmg/oIy1iFBfg9AerB2Cx8HMOs6R/wbTSTjywaNnK11/loeJO\n"+
 "mksakeECgYEA1JqYXhQvhSIRVgx/JwO7T0drFjiENhp0ZGxl02YUwJ9RNesvW9XG\n"+
 "pQ783wK7XQqhfXyOZbtNNh3IAx4gA8fsLP3V2FnVLRaqIzoEddE1azWyJicWn/xN\n"+
 "CXjiWRZSuUw4nJh37UIg3ZFON950PeYYBf6MdQ70JNwv8QHvjaQ8blkCgYEAskTA\n"+
 "7ssHlRHJd7OQskKdHGn3dIzs0HzWy6PhBKBIq6i5HloXknN9ccz2lam0XTstyQHe\n"+
 "8m/B4xkHoHR9kzQ6mYRzkkNn0XSsKpklL3fBM28YetNKp98PWbQGwngHWnSTjykB\n"+
 "U84z3ITYoLww8Umqo2E8z5I31TWhdKVsXrf+gt8CgYEAwtltC8w0QTAX82VAo+En\n"+
 "qmEqJBNoGK3mPqOaL0U/f8qEA4aowOIMTPqPh1RZo8w6dWfSmioveYmR5kmVzeFM\n"+
 "828wrjimx8wd4msp61DyRG/R9mWctsPlVukTidKLvoe82shiIXCZ7ndd2JZostJO\n"+
 "8i95S0+JMr8asSe1BXugZwECgYEAkKoIov+xnH/9rRzT3EUAeDFp9XPzLVFRgZnw\n"+
 "na9wqZTW/NGBdYAxGWuJ43P/APVoN0lEYhrdDWyfas99pDprH2+D1frLQgdbO4ph\n"+
 "yb5100ZhaDMUz1vNei8EmUWMOhSGNlPBVv/ryBUqMm3ljbkJN2JOVefDVUsbRayE\n"+
 "X+0SJaMCgYEApW9gTkKL/yUScQIuMgALCNLGEkh4q6mp1LkJblJtGut3UAEqWtK+\n"+
 "7YxOaM4q41E/+ZRwNJWR2gj6mwPOhhSJS6RNB5q4UkYuMB5dkFA26d+BeJt5nqDy\n"+
 "mViirQJj/HfPTgcLFZaZsnRw+cc2BnUIyWFL9XhomGYWEyjbjUNyo1Y=\n"+
 "-----END RSA PRIVATE KEY-----"
 const publicKey = 
 "-----BEGIN PUBLIC KEY-----\n"+
 "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlAyXPCWjgKT5+ai0/rdQ\n"+
 "P6v7vftFGZN758n+mjDxTF+xqFKg27JlEsSrlsigx8HBZi9SdI/5MG4MuNTIYi3w\n"+
 "ezKx477K6KBKesrv5uceyvXU+VtM/8Dkq5NB4FgdJW0kY1ij0e7cIomZGqEbMy5/\n"+
 "dRMiUHv5PK9DfpxJETmIwW1//2TgjU8LVReJmcMdaFEMZavJ+qISYnyO5VAavvry\n"+
 "VgznJfYBhl7nmJ8fgWjhr+znB8i3RrB05rrwI03mB9TyT1o/hHj1lIWpau4LMjKf\n"+
 "CRF+Nz7nfM2uYdYD87iDM6qGdAcT8AkbzIhuc7xKkYjjkS/y4MEob4gbYNFN2/tR\n"+
 "hwIDAQAB\n"+
 "-----END PUBLIC KEY-----"

 const privateKey2 = 
 
"-----BEGIN RSA PRIVATE KEY-----\n"+
"MIICoDCCAYigAwIBAgIESZYC0jANBgkqhkiG9w0BAQ0FADASMRAwDgYDVQQDEwdteUlkS2V5MB4X\n"+
"DTIyMDUwNzE1NDUzN1oXDTMyMDUwNzE1NDUzN1owEjEQMA4GA1UEAxMHbXlJZEtleTCCASIwDQYJ\n"+
"KoZIhvcNAQEBBQADggEPADCCAQoCggEBAJ4DsZcSEGZAGDin8z41M5OtXCSGjJbma5b98Y2eSIOh\n"+
"xQnaz4hbuLzd7RIQ0mC7D/W66lTnSoc0XL1vjw5qToeAHPbtAYI2heapn55Aj9p18GdEga/tt2d6\n"+
"eDVodyjuVNWbBuwMjwi8DqX2pQavEQnzb3XZcVM4KWz+DoMgc5eF3ATa3AClUBgLSwDdBiN9I5uz\n"+
"/5EPMGc8t4h0hdQ0+qJQX6a66G6Vj2f2PIrGQyc6dybLUOPGEEDDstW3Oj/PgpR00F7OtDDw15pB\n"+
"gdwEWpGFTVB6GEwkPvhi6sxE0g9c9gYrf9LDWeZ11inPzl0Z20bWgaa/0kp7iu5hoRIzE6sCAwEA\n"+
"ATANBgkqhkiG9w0BAQ0FAAOCAQEAZIneqWquxXp4scyejhB1FfzKBxZCr31+hfuSpmVOPU/aayx/\n"+
"3Luf+M/GHDzr0aG9Mu55kTSirmLRfHwQOhhAdtKoV/SQGa6bFRCuf0H9q/aYXowOLQX8PueGJ6AD\n"+
"LikyjPZLjmchylZWvVv8fpf9r6tIOyjQ5MvOqSXsrx/y2IW1ouD7rNag3ICt9EzECShvx2Xjx1w9\n"+
"hA7htHy26dC+vYdL23rjcfeLJWjTc/8VGEEtUQ87cqrVPhedZ+DJXfShm7HtD/pzZernVdRaGNZv\n"+
"daWJyKW7y9U7XUxPNXlcleLSAA4TC9yyrZKJervZ0WWvVwZzaF2lamtu3Wn6z9qOnA==\n"+
"-----END RSA PRIVATE KEY-----";

const publicKey2 =
"-----BEGIN PUBLIC KEY-----\n"+
"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAngOxlxIQZkAYOKfzPjUzk61cJIaMluZr\n"+
"lv3xjZ5Ig6HFCdrPiFu4vN3tEhDSYLsP9brqVOdKhzRcvW+PDmpOh4Ac9u0BgjaF5qmfnkCP2nXw\n"+
"Z0SBr+23Z3p4NWh3KO5U1ZsG7AyPCLwOpfalBq8RCfNvddlxUzgpbP4OgyBzl4XcBNrcAKVQGAtL\n"+
"AN0GI30jm7P/kQ8wZzy3iHSF1DT6olBfprrobpWPZ/Y8isZDJzp3JstQ48YQQMOy1bc6P8+ClHTQ\n"+
"Xs60MPDXmkGB3ARakYVNUHoYTCQ++GLqzETSD1z2Bit/0sNZ5nXWKc/OXRnbRtaBpr/SSnuK7mGh\n"+
"EjMTqwIDAQAB\n"+
"-----END PUBLIC KEY-----";

const EncriptionService = new EncryptionService(crypto);
const input = "olá o meu nome é joão";
const output = EncriptionService.rsaEncrypt(input,privateKey);
console.log(output);
const decrypted = EncriptionService.rsaDecrypt(output,publicKey);
console.log(decrypted);