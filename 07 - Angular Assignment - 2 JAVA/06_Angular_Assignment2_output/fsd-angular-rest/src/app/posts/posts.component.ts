import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { ApiService } from '../shared/services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  userNames: any;
  optionSelected: any;
  indexSelected: any;
  postForm: any;
  postsDetails = [];
  title: string = '';
  body: string = '';
  updatedPost: any;

  constructor(private apiService: ApiService,
    private formBuilder: FormBuilder,
    private router: Router) {
      
    this.postForm = this.formBuilder.group({
      'userId': ['', Validators.required],
      'title': ['', [Validators.required]],
      'body': ['', [Validators.required]]
    });
  }


  ngOnInit() {
    this.apiService.getUsers().subscribe(users => {
      const userList = Object.keys(users).map(key => users[key]);
      this.userNames = userList.map(user => user.name);
    });

    this.apiService.getPosts().subscribe(posts => {
      this.postsDetails = Object.keys(posts).map(index => posts[index]);
    });
  }

  onOptionsSelected (event) {
    this.optionSelected = event.target.value;
    this.indexSelected = this.userNames.indexOf(this.optionSelected);
  }

  addOrUpdatePost() {
    this.updatedPost ? this.updatePost() : this.addPost();
  }

  addPost() {
    const newPost = this.postForm.value;
    newPost.userId = this.userNames.indexOf(newPost.userId);
    this.apiService.addPost(newPost).subscribe(post => {
      this.postsDetails = this.postsDetails.concat(post);
      this.postForm.reset();
      alert('Post added successfully!!')
    });
  }

  deletePost(postToDelete) {
    this.apiService.deletePost(postToDelete.id).subscribe(() => {
      this.postsDetails = this.postsDetails.filter(post => post !== postToDelete);
      this.postForm.reset();
      alert('Post deleted successfully!!')
    });
  }

  clickPost(post) {
    this.router.navigate(['post', post.id]);
  }

  loadPost(post) {
    this.updatedPost = post;
    this.optionSelected = this.userNames[post.userId];
    this.title = post.title;
    this.body = post.body;
  }

  updatePost() {
    const updatedPost = this.postForm.value;
    updatedPost.userId = this.userNames.indexOf(updatedPost.userId);
    updatedPost.id = this.updatedPost.id;
    this.apiService.updatePost(updatedPost).subscribe(response => {
      const updatedPostDetails = this.postsDetails.find(post => post.id === updatedPost.id);
      const updatedIndex = this.postsDetails.indexOf(updatedPostDetails);
      this.postsDetails[updatedIndex].userId = response['userId'];
      this.postsDetails[updatedIndex].body = response['body'];
      this.postsDetails[updatedIndex].title = response['title'];
      this.postsDetails[updatedIndex].id = response['id'];
      
      this.postForm.reset();
      this.updatedPost = null;
      alert('Post updated successfully!!')
    });
  }

}
