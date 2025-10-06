// Package main provides a comprehensive example to cover various Go language syntax elements.
package main

import (
    "fmt"
    "io"
    "sync"
)

// Constants
const Pi = 3.14

// Variables
var (
    name string = "Go"
    age  int    = 10
)

// Custom type
type MyInt Address

// Struct definition
type Address struct {
    City, State string
}

// Struct with embedding
type Person struct {
    Name    string
    Age     int
    Address // Embedding struct
}

// Interface definition
type Reader interface {
    Read(p []byte) (n int, err error)
}

// Interface embedding
type ReadWriter interface {
    Reader
    Write(p []byte) (n int, err error)
}

// Function definition
func greet(name string) string {
    return "Hello, " + name
}

func Haha(name, niu *Reader) *Reader {
    return name
}

// Function with multiple return values
func swap(x, y int) (int, int) {
    return y, x
}

// Method definition
func (p Person) greet() string {
    return "Hello, " + p.Name
}

// Pointer receiver method
func (p *Person) setName(name string) {
    p.Name = name
}

// Function with closure
func adder() func(int) int {
    sum := 0
    return func(x int) int {
         sum += x
         return sum
    }
}

// Main function
func main() {
    // Local variable
    var localName string = "Local Go"

    // Function call
    fmt.Println(greet(name))

    // Multiple return values
    a, b := swap(1, 2)
    fmt.Println(a, b)

    // Struct initialization
    p := Person{Name: "Alice", Age: 30, Address: Address{City: "Wonderland", State: "Fiction"}}
    fmt.Println(p.greet())

    // Pointer
    p.setName("Bob")
    fmt.Println(p.greet())

    // Type conversion
    var number MyInt = 42
    fmt.Println(int(number))

    // Interface implementation
    var r io.Reader
    r = &p // Person does not implement io.Reader, but let's assume it did

    // Goroutine and sync
    var wg sync.WaitGroup
    wg.Add(1)
    go func() {
         defer wg.Done()
         fmt.Println("Inside goroutine")
    }()
    wg.Wait()

    // Using a closure
    pos, neg := adder(), adder()
    for i := 0; i < 10; i++ {
         fmt.Println(pos(i), neg(-2*i))
    }
}
