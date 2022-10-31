db = db.getSiblingDB('library');

db.createCollection('book');

db.book.insertMany([
  {
    name: 'The Hunger Games',
    author: 'Suzanne Collins'
  },
  {
    name: 'Harry Potter and the Order of the Phoenix',
    author: 'J.K. Rowling'
  },
  {
    name: 'Pride and Prejudice',
    author: 'Jane Austen'
  },
  {
    name: 'To Kill a Mockingbird',
    author: 'Harper Lee'
  },
  {
    name: 'The Book Thief',
    author: 'Markus Zusak'
  },
  {
    name: 'Twilight',
    author: 'Stephenie Meyer'
  },
  {
    name: 'Animal Farm',
    author: 'George Orwell'
  },
  {
    name: 'The Chronicles of Narnia',
    author: 'C.S. Lewis'
  },
  {
    name: 'The Fault in Our Stars',
    author: 'John Green'
  },
  {
    name: 'Gone with the Wind',
    author: 'Margaret Mitchell'
  },
]);