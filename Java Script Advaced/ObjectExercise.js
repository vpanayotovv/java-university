function solve(input){
    let heroes = [];
    for (let line of input) {
        let[name,level,items] = line.split(' / ');
        level = Number(level);
        items = items ? items.split(', ') : [] ;
        heroes.push({name,level,items})
    }
    return JSON.stringify(heroes);
}

function makingJuice(input){
    let juices = {};
    let bottles = {};
    input.forEach(element => {
        let [ name , quantity ] =  element.split(' => ');
        quantity = Number(quantity);s
        if(!juices.hasOwnProperty(name)){
            juices[name] = 0;
        }
        juices[name] += quantity;
        if(juices[name] >= 1000){
            bottles[name] = Math.trunc(juices[name] / 1000 );
        }
    });
    return bottles;
}

function sort(input){
    let cataloge = {}
    for (const line of input) {
        let [ name , price ] = line.split(' : ');
        price = Number(price);
        let initial = name[0];

        if(!cataloge.hasOwnProperty(initial)){
            cataloge[initial] = {};
        }
        let products = cataloge[initial];
        products[name] = price;
    }
    return Object.keys(products).sort();
}