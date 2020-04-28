function print(input) {
    let separator = input[input.length - 1]
    input.splice(input.length - 1,1)
    console.log(input.join(separator))
}

function printNum(input){
    let lastNum = +input.pop();
    for (let i = 0; i < input.length; i+= lastNum) {
        console.log(input[i])
    }
}

function addAndRemove(input){
    let counter = 0;
    let newArr = [];
    for (let i = 0; i < input.length; i++) {
        counter++;
        if(input[i] === 'add'){
            newArr.push(counter);
        } else if (input[i] === 'remove'){
            newArr.pop();
        }
    }
    if(newArr.length == 0){
        return console.log('Empty')
    }else{
    
    return console.log(newArr.join('\n').trim());
    }
}
function rotateArray(input){
    let rotations = (+input[input.length - 1]) % (input.length - 1)
    let arr = input.slice(0,input.length-1)
    for (let i = 0; i < +rotations; i++) {
        arr.unshift(arr.pop()   )
    }
    console.log(arr.join(' '))
}