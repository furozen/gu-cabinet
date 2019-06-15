// typescripted from https://github.com/greybax/cyrillic-to-translit-js/blob/master/CyrillicToTranslit.js
class CyrillicToTranslit
{

  static _firstLetterAssociations = {
    "а":"a",
    "б":"b",
    "в":"v",
    "ґ":"g",
    "г":"g",
    "д":"d",
    "е":"e",
    "ё":"e",
    "є":"ye",
    "ж":"zh",
    "з":"z",
    "и":"i",
    "і":"i",
    "ї":"yi",
    "й":"i",
    "к":"k",
    "л":"l",
    "м":"m",
    "н":"n",
    "о":"o",
    "п":"p",
    "р":"r",
    "с":"s",
    "т":"t",
    "у":"u",
    "ф":"f",
    "х":"h",
    "ц":"c",
    "ч":"ch",
    "ш":"sh",
    "щ":"sh'",
    "ъ":"",
    "ы":"i",
    "ь":"",
    "э":"e",
    "ю":"yu",
    "я":"ya",
  };


  static transform(input, spaceReplacement='') {
    if (!input) {
      return "";
    }
    const _associations = Object.assign({}, this._firstLetterAssociations);
    // We must normalize string for transform all unicode chars to uniform form
    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/normalize
    const normalizedInput = input.normalize();

    let newStr = "";
    for (let i = 0; i < normalizedInput.length; i++) {
      const isUpperCaseOrWhatever = normalizedInput[i] === normalizedInput[i].toUpperCase();
      let strLowerCase = normalizedInput[i].toLowerCase();
      if (strLowerCase === " " && spaceReplacement) {
        newStr += spaceReplacement;
        continue;
      }
      let newLetter = (i === 0 ? this._firstLetterAssociations : _associations)[strLowerCase];
      if ("undefined" === typeof newLetter) {
        newStr += isUpperCaseOrWhatever ? strLowerCase.toUpperCase() : strLowerCase;
      } else {
        newStr += isUpperCaseOrWhatever ? newLetter.toUpperCase() : newLetter;
      }
    }
    return newStr;
  }
}
export class Utils {
  static translitate(value, space=''){
    return CyrillicToTranslit.transform(value, space);
  }
}
